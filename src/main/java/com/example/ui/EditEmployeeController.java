package com.example.ui;

import com.example.Employee;
import com.example.EmployeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="mailto:mlassiter@lassitercg.com">Mark Lassiter</a>
 */
@RequestScoped
@Named
public class EditEmployeeController {

	@Inject
	EmployeeForm employeeForm;

	@Inject
	EmployeeRepository employeeRepository;

	public void save() {
		employeeRepository.merge(employeeForm.getEmployee());
	}

	public void preRenderViewEvent() {
		if (employeeForm.getEmployee() == null) {
			initializeEmployee();
		}
	}

	private void initializeEmployee() {
		if (employeeForm.getEmployeeId() == null) {
			employeeForm.setEmployee(new Employee());
			return;
		}
		Employee employee = employeeRepository.find(employeeForm.getEmployeeId());
		employeeForm.setEmployee(employee);
	}
}