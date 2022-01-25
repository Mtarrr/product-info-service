package com.github.mtarrr.pis.entity.binding;

import com.github.mtarrr.pis.entity.ProductOfferingBody;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;

import java.sql.SQLException;

public class JSONBProductOfferingBodyBinding implements Binding<String, ProductOfferingBody> {
    @Override
    public @NotNull Converter<String, ProductOfferingBody> converter() {
        return new JSONBProductOfferingBodyConverter();
    }

    @Override
    public void sql(BindingSQLContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void register(BindingRegisterContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void set(BindingSetStatementContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void set(BindingSetSQLOutputContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void get(BindingGetResultSetContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void get(BindingGetStatementContext<ProductOfferingBody> ctx) throws SQLException {

    }

    @Override
    public void get(BindingGetSQLInputContext<ProductOfferingBody> ctx) throws SQLException {

    }
}
