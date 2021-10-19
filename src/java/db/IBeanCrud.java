/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IBeanCrud<TBean> {
    public void Add(HashMap<String, String> parameters , Connection con) throws SQLException;
    public void Remove(String id, Connection con) throws SQLException;
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException;
    public TBean GetById (int id, Connection con) throws SQLException;
    public List<TBean> GetAll(Connection con) throws SQLException;
}
