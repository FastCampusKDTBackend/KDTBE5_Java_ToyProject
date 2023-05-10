package me.smartstore.core.service;

import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.Parameter;
import me.smartstore.core.manager.CustomerGroupManager;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

public class CustomerGroupService {
  private static final CustomerGroupService customerGroupService = new CustomerGroupService();
  private final CustomerGroupManager customerGroupManager = CustomerGroupManager.getInstance();

  public static CustomerGroupService getInstance() {
    return customerGroupService;
  }

  public CustomerGroup setParameter(CustomerType customerType, Parameter parameter)
      throws StoreException {
    return customerGroupManager.save(customerType, parameter);
  }

  public CustomerGroup find(CustomerType customerType) throws StoreException {
    return customerGroupManager.select(customerType);
  }

  public CustomerGroup[] findAll() throws StoreException {
    return customerGroupManager.selectAll();
  }
}
