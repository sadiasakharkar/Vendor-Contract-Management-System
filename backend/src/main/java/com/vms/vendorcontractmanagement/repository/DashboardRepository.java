package com.vms.vendorcontractmanagement.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface DashboardRepository extends Repository<com.vms.vendorcontractmanagement.entity.Contract, Long> {
  @Query("select count(v), sum(case when v.status = 'ACTIVE' then 1 else 0 end) from Vendor v")
  Object[] vendorCounts();
  @Query("select count(c), coalesce(sum(c.contractValue), 0), sum(case when c.endDate < :today then 1 else 0 end) from Contract c")
  Object[] contractSummary(LocalDate today);
  @Query("select coalesce(v.category, 'Uncategorized'), count(v) from Vendor v group by v.category order by count(v) desc")
  List<Object[]> vendorsByCategory();
  @Query("select c.status, count(c) from Contract c group by c.status order by count(c) desc")
  List<Object[]> contractsByStatus();
  @Query("select d.departmentName, coalesce(sum(c.contractValue), 0) from Department d join d.vendors v join Contract c on c.vendor = v group by d.id, d.departmentName order by sum(c.contractValue) desc")
  List<Object[]> spendingByDepartment();
  @Query("select v.vendorName, coalesce(sum(c.contractValue), 0) from Vendor v left join Contract c on c.vendor = v group by v.id, v.vendorName order by sum(c.contractValue) desc")
  List<Object[]> spendingByVendor();
}
