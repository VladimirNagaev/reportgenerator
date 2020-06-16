import java.io.File;
import java.util.List;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class ReportConfig {
    private short width;
    private short height;
    private List<ColumnConfig> columnConfigs;

    public ReportConfig() {
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public List<ColumnConfig> getColumnConfigs() {
        return columnConfigs;
    }

    public void setColumnConfigs(List<ColumnConfig> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }


}
