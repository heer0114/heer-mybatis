/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package me.heer.mybatis.excutor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Executor实现的基�?；功能包括：创建statement对象，为sql语句绑定参数，执行增删改查等SQL语句、将结果映射集进行转�?
 * @author Clinton Begin
 * 
 */
public interface StatementHandler {

 //从连接中获取�?个Statement
 PreparedStatement prepare(Connection connection) throws SQLException;


   //执行select语句
  ResultSet query(PreparedStatement statement);




}
