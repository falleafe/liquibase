package liquibase.change;

import liquibase.database.sql.SqlStatement;
import liquibase.database.sql.RawSqlStatement;
import liquibase.database.Database;
import liquibase.database.structure.DatabaseObject;
import liquibase.exception.UnsupportedChangeException;
import liquibase.exception.RollbackImpossibleException;
import liquibase.exception.SetupException;

import java.util.Set;

public class ExampleCustomChange implements CustomChange {

    private String tableName;
    private String columnName;
    private String newValue;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public SqlStatement[] generateStatements(Database database) throws UnsupportedChangeException {
        return new SqlStatement[]{
                new RawSqlStatement("update "+tableName+" set "+columnName+" = "+newValue)
        };
    }

    public SqlStatement[] generateRollbackStatements(Database database) throws UnsupportedChangeException, RollbackImpossibleException {
        return new SqlStatement[]{
                new RawSqlStatement("update "+tableName+" set "+columnName+" = null")
        };
    }

    public boolean canRollBack() {
        return true;
    }

    public String getConfirmationMessage() {
        return "Custom class updated "+tableName+"."+columnName;
    }

    public void setUp() throws SetupException {
    }

    public Set<DatabaseObject> getAffectedDatabaseObjects() {
        return null;
    }
}
