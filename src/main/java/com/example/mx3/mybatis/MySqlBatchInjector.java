package com.example.mx3.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;

/**
 * @author yunong.byn
 * @version 1.0
 * @since 2022/11/14 15:02
 */
public class MySqlBatchInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new InsertListMethod());
        return methodList;
    }

    private static class InsertListMethod extends AbstractMethod {

        protected InsertListMethod() {
            super("insertList");
        }

        @Override
        public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
            final String sql = "<script>insert into %s %s values %s</script>";
            final String fieldSql = prepareFieldSql(tableInfo);
            final String valueSql = prepareValuesSql(tableInfo);
            final String sqlResult = String.format(sql, tableInfo.getTableName(), fieldSql, valueSql);
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
            // 第三个参数必须和RootMapper的自定义方法名一致
            return this.addInsertMappedStatement(mapperClass, modelClass, "insertList", sqlSource, new NoKeyGenerator(), null, null);
        }

        private String prepareFieldSql(TableInfo tableInfo) {
            StringBuilder fieldSql = new StringBuilder();
            fieldSql.append(tableInfo.getKeyColumn()).append(",");
            tableInfo.getFieldList().forEach(x -> {
                fieldSql.append(x.getColumn()).append(",");
            });
            fieldSql.delete(fieldSql.length() - 1, fieldSql.length());
            fieldSql.insert(0, "(");
            fieldSql.append(")");
            return fieldSql.toString();
        }

        private String prepareValuesSql(TableInfo tableInfo) {
            final StringBuilder valueSql = new StringBuilder();
            valueSql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
            valueSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
            tableInfo.getFieldList().forEach(x -> valueSql.append("#{item.").append(x.getProperty()).append("},"));
            valueSql.delete(valueSql.length() - 1, valueSql.length());
            valueSql.append("</foreach>");
            return valueSql.toString();
        }
    }

}

