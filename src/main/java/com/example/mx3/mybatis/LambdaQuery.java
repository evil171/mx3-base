package com.example.mx3.mybatis;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.core.enums.SqlKeyword.ORDER_BY;

/**
 * lambda查询器
 *
 * @author yunong.byn
 * @since 2021/7/13 5:07 下午
 */
public class LambdaQuery<T> extends AbstractLambdaWrapper<T, LambdaQuery<T>>
        implements Query<LambdaQuery<T>, T, SFunction<T, ?>> {

    /**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    public LambdaQuery() {
        this((T) null);
    }

    public LambdaQuery(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public LambdaQuery(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
    }

    LambdaQuery(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
                Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    /**
     * SELECT 部分 SQL 设置
     *
     * @param columns 查询字段
     */
    @SafeVarargs
    @Override
    public final LambdaQuery<T> select(SFunction<T, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(columnsToString(false, columns));
        }
        return typedThis;
    }

    /**
     * 过滤查询的字段信息(主键除外!)
     * <p>例1: 只要 java 字段名以 "test" 开头的             -> select(i -&gt; i.getProperty().startsWith("test"))</p>
     * <p>例2: 只要 java 字段属性是 CharSequence 类型的     -> select(TableFieldInfo::isCharSequence)</p>
     * <p>例3: 只要 java 字段没有填充策略的                 -> select(i -&gt; i.getFieldFill() == FieldFill.DEFAULT)</p>
     * <p>例4: 要全部字段                                   -> select(i -&gt; true)</p>
     * <p>例5: 只要主键字段                                 -> select(i -&gt; false)</p>
     *
     * @param predicate 过滤方式
     * @return this
     */
    @Override
    public LambdaQuery<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        if (entityClass == null) {
            entityClass = getEntityClass();
        } else {
            setEntityClass(entityClass);
        }
        Assert.notNull(entityClass, "entityClass can not be null");
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(entityClass).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    /**
     * 用于生成嵌套 sql
     * <p>故 sqlSelect 不向下传递</p>
     */
    @Override
    protected LambdaQuery<T> instance() {
        return new LambdaQuery<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
    }

    public LambdaQuery<T> eq(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.eq(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> ne(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.ne(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> gt(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.gt(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> ge(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.ge(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> lt(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.lt(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> le(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.le(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> like(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.like(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> notLike(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.notLike(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> likeLeft(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.likeLeft(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> likeRight(boolean condition, SFunction<T, ?> column, Supplier<?> val) {
        if (condition) {
            return super.likeRight(column, val.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> between(boolean condition, SFunction<T, ?> column, Supplier<?> val1, Supplier<?> val2) {
        if (condition) {
            return super.between(column, val1.get(), val2.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> notBetween(boolean condition, SFunction<T, ?> column, Supplier<?> val1, Supplier<?> val2) {
        if (condition) {
            return super.notBetween(column, val1.get(), val2.get());
        }
        return typedThis;
    }

    public LambdaQuery<T> in(boolean condition, SFunction<T, ?> column, Object... values) {
        return super.in(condition, column, values);
    }

    public LambdaQuery<T> in(boolean condition, SFunction<T, ?> column, Collection<?> coll) {
        return super.in(condition, column, coll);
    }

    public LambdaQuery<T> notIn(boolean condition, SFunction<T, ?> column, Collection<?> coll) {
        return super.notIn(condition, column, coll);
    }

    public LambdaQuery<T> notIn(boolean condition, SFunction<T, ?> column, Object... values) {
        return super.notIn(condition, column, values);
    }

    public LambdaQuery<T> limit() {
        return this.limit(1);
    }

    public LambdaQuery<T> limit(int start) {
        return super.last(String.format("LIMIT %s", start));
    }

    public LambdaQuery<T> limit(int start, int offset) {
        return super.last(String.format("LIMIT %s %s", start, offset));
    }

    public LambdaQuery<T> limit(boolean condition) {
        if (condition) {
            return this.limit(1);
        }
        return typedThis;
    }

    public LambdaQuery<T> limit(boolean condition, int start) {
        if (condition) {
            return super.last(String.format("LIMIT %s", start));
        }
        return typedThis;
    }

    public LambdaQuery<T> limit(boolean condition, int start, int offset) {
        if (condition) {
            return super.last(String.format("LIMIT %s %s", start, offset));
        }
        return typedThis;
    }

    public LambdaQuery<T> orderByField(SFunction<T, ?> column, Object... values) {
        String columnText = super.columnToString(column);
        String collText = Arrays.stream(values)
                .map(item -> String.format("'%s'", item))
                .collect(Collectors.joining(","));
        return super.maybeDo(true,
                () -> super.appendSqlSegments(ORDER_BY, () -> String.format("FIELD(%s,%s)", columnText, collText)));
    }

}
