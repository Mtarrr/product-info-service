package com.github.mtarrr.pis.model.entity.binding;

import com.github.mtarrr.pis.model.entity.ProductOfferingBody;
import com.github.mtarrr.pis.model.utils.CommonUtils;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Types;
import java.util.Objects;

public class JSONBProductOfferingBodyBinding implements Binding<String, ProductOfferingBody> {
    @Override
    public @NotNull Converter<String, ProductOfferingBody> converter() {
        return new JSONBProductOfferingBodyConverter(CommonUtils.buildObjectMapper());
    }

    @Override
    public void sql(BindingSQLContext<ProductOfferingBody> ctx) {
        if (ctx.render().paramType() == ParamType.INLINED)
            ctx.render().visit(DSL.inline(ctx.convert(converter()).value())).sql("::jsonb");
        else
            ctx.render().sql(ctx.variable()).sql("::jsonb");
    }

    @Override
    public void register(BindingRegisterContext<ProductOfferingBody> ctx) throws SQLException {
        ctx.statement().registerOutParameter(ctx.index(), Types.TIMESTAMP_WITH_TIMEZONE);
    }

    @Override
    public void set(BindingSetStatementContext<ProductOfferingBody> ctx) throws SQLException {
        ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
    }

    @Override
    public void set(BindingSetSQLOutputContext<ProductOfferingBody> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void get(BindingGetResultSetContext<ProductOfferingBody> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
    }

    @Override
    public void get(BindingGetStatementContext<ProductOfferingBody> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.statement().getString(ctx.index()));
    }

    @Override
    public void get(BindingGetSQLInputContext<ProductOfferingBody> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
