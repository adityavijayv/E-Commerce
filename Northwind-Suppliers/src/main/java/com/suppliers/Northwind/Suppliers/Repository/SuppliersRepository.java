package com.suppliers.Northwind.Suppliers.Repository;

import com.suppliers.Northwind.Suppliers.Model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {


}
