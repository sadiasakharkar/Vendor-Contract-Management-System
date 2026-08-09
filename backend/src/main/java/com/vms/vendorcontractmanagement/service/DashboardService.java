package com.vms.vendorcontractmanagement.service;

import com.vms.vendorcontractmanagement.repository.ContractRepository;
import com.vms.vendorcontractmanagement.repository.DashboardRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class DashboardService {
  private final DashboardRepository dashboard;
  private final ContractRepository contracts;
  public Map<String, Object> summary() {
    Object[] vendors = dashboard.vendorCounts(); Object[] contract = dashboard.contractSummary(LocalDate.now());
    return Map.of("totalVendors", vendors[0], "activeVendors", vendors[1], "totalContracts", contract[0], "totalContractValue", contract[1] == null ? BigDecimal.ZERO : contract[1], "expiringIn30Days", contracts.expiring(LocalDate.now(), LocalDate.now().plusDays(30)).size(), "expiringIn90Days", contracts.expiring(LocalDate.now(), LocalDate.now().plusDays(90)).size(), "expiredContracts", contract[2]);
  }
  public List<Map<String,Object>> categoryCounts() { return dashboard.vendorsByCategory().stream().map(r -> Map.of("category", r[0], "count", r[1])).toList(); }
  public List<Map<String,Object>> statusCounts() { return dashboard.contractsByStatus().stream().map(r -> Map.of("status", r[0], "count", r[1])).toList(); }
  public List<Map<String,Object>> departmentSpending() { return dashboard.spendingByDepartment().stream().map(r -> Map.of("departmentName", r[0], "totalSpending", r[1])).toList(); }
  public List<Map<String,Object>> vendorSpending() { return dashboard.spendingByVendor().stream().map(r -> Map.of("vendorName", r[0], "totalContractValue", r[1])).toList(); }
}
