package com.vms.vendorcontractmanagement.controller;

import com.vms.vendorcontractmanagement.dto.ContractDtos;
import com.vms.vendorcontractmanagement.service.ContractService;
import com.vms.vendorcontractmanagement.service.DashboardService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/dashboard") @RequiredArgsConstructor
public class DashboardController {
  private final DashboardService dashboard; private final ContractService contracts;
  @GetMapping("/summary") public Map<String,Object> summary() { return dashboard.summary(); }
  @GetMapping("/vendors-by-category") public List<Map<String,Object>> categories() { return dashboard.categoryCounts(); }
  @GetMapping("/contracts-by-status") public List<Map<String,Object>> statuses() { return dashboard.statusCounts(); }
  @GetMapping("/spending-by-department") public List<Map<String,Object>> departmentSpending() { return dashboard.departmentSpending(); }
  @GetMapping("/spending-by-vendor") public List<Map<String,Object>> vendorSpending() { return dashboard.vendorSpending(); }
  @GetMapping("/expiring-contracts") public List<ContractDtos.Response> expiring(@RequestParam(defaultValue="30") int days) { return contracts.expiring(days); }
}
