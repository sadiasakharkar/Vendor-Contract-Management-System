const baseUrl = (process.env.NEXT_PUBLIC_API_URL ?? 'http://localhost:8080/api').replace(/\/$/, '')

export type Page<T> = { content: T[]; totalElements: number; totalPages: number }
export type Vendor = { id:number; vendorCode:string; vendorName:string; category:string|null; contactEmail:string|null; phone:string|null; country:string|null; status:string; ownerUserId:number|null; ownerName:string|null; departments:string[] }
export type Contract = { id:number; contractNumber:string; vendorId:number; vendorName:string; startDate:string; endDate:string; contractValue:number; paymentFrequency:string; status:string; renewalStatus:string; remainingDays:number; priority:string }
export type Department = { id:number; departmentName:string; managerName:string|null }
export type Summary = { totalVendors:number; activeVendors:number; totalContracts:number; totalContractValue:number; expiringIn30Days:number; expiringIn90Days:number; expiredContracts:number }
export type CountRow = { category?:string; status?:string; count:number }
export type SpendingRow = { departmentName?:string; vendorName?:string; totalSpending?:number; totalContractValue?:number }
export type AuthResponse = { token:string; tokenType:string; name:string; role:string }
export type VendorInput = Omit<Vendor, 'id'|'ownerName'|'departments'>
export type ContractInput = Omit<Contract, 'id'|'vendorName'|'remainingDays'|'priority'>

async function request<T>(path:string, token?:string, init?:RequestInit):Promise<T> {
  const response = await fetch(`${baseUrl}${path}`, { ...init, headers: { 'Content-Type':'application/json', ...(token ? { Authorization:`Bearer ${token}` } : {}), ...init?.headers } })
  if (response.status === 401 || response.status === 403) throw new Error('Your session is no longer authorized. Please sign in again.')
  if (!response.ok) { const body = await response.json().catch(() => null); throw new Error(body?.message ?? `Request failed (${response.status})`) }
  return response.status === 204 ? undefined as T : response.json() as Promise<T>
}
export const auth = { login:(email:string,password:string) => request<AuthResponse>('/auth/login', undefined, {method:'POST',body:JSON.stringify({email,password})}) }
export const api = {
  vendors:(token:string, search='') => request<Page<Vendor>>(`/vendors?size=100${search ? `&search=${encodeURIComponent(search)}`:''}`,token), vendor:(id:number,token:string)=>request<Vendor>(`/vendors/${id}`,token), createVendor:(input:VendorInput,token:string)=>request<Vendor>('/vendors',token,{method:'POST',body:JSON.stringify(input)}), updateVendor:(id:number,input:VendorInput,token:string)=>request<Vendor>(`/vendors/${id}`,token,{method:'PUT',body:JSON.stringify(input)}), deleteVendor:(id:number,token:string)=>request<void>(`/vendors/${id}`,token,{method:'DELETE'}), toggleVendor:(id:number,status:string,token:string)=>request<Vendor>(`/vendors/${id}/status?status=${status}`,token,{method:'PUT'}),
  contracts:(token:string)=>request<Page<Contract>>('/contracts?size=100',token), createContract:(input:ContractInput,token:string)=>request<Contract>('/contracts',token,{method:'POST',body:JSON.stringify(input)}), updateContract:(id:number,input:ContractInput,token:string)=>request<Contract>(`/contracts/${id}`,token,{method:'PUT',body:JSON.stringify(input)}), deleteContract:(id:number,token:string)=>request<void>(`/contracts/${id}`,token,{method:'DELETE'}), expiring:(days:number,token:string)=>request<Contract[]>(`/contracts/expiring?days=${days}`,token),
  departments:(token:string)=>request<Department[]>('/departments',token), createDepartment:(input:Omit<Department,'id'>,token:string)=>request<Department>('/departments',token,{method:'POST',body:JSON.stringify(input)}), updateDepartment:(id:number,input:Omit<Department,'id'>,token:string)=>request<Department>(`/departments/${id}`,token,{method:'PUT',body:JSON.stringify(input)}), deleteDepartment:(id:number,token:string)=>request<void>(`/departments/${id}`,token,{method:'DELETE'}), departmentVendors:(id:number,token:string)=>request<Vendor[]>(`/departments/${id}/vendors`,token), assignVendor:(departmentId:number,vendorId:number,token:string)=>request<void>(`/departments/${departmentId}/vendors/${vendorId}`,token,{method:'POST'}), removeVendor:(departmentId:number,vendorId:number,token:string)=>request<void>(`/departments/${departmentId}/vendors/${vendorId}`,token,{method:'DELETE'}),
  summary:(token:string)=>request<Summary>('/dashboard/summary',token), categories:(token:string)=>request<CountRow[]>('/dashboard/vendors-by-category',token), statuses:(token:string)=>request<CountRow[]>('/dashboard/contracts-by-status',token), departmentSpending:(token:string)=>request<SpendingRow[]>('/dashboard/spending-by-department',token), vendorSpending:(token:string)=>request<SpendingRow[]>('/dashboard/spending-by-vendor',token), expiringDashboard:(days:number,token:string)=>request<Contract[]>(`/dashboard/expiring-contracts?days=${days}`,token),
}
