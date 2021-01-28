package com.qfedu.one2many;

import java.util.List;

public interface DepartmentDao {
    Department findByDid1(Integer did);
    Department findByDid2(Integer did);
    Department findByDid3(Integer did);
    List<Department> findAll();
}
