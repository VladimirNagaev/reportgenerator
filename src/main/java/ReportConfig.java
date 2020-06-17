import java.util.List;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class ReportConfig {
    private Integer width;
    private Integer height;
    private List<ColumnConfig> columnConfigs;

    public ReportConfig() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<ColumnConfig> getColumnConfigs() {
        return columnConfigs;
    }

    public void setColumnConfigs(List<ColumnConfig> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }



    @Override
    public String toString() {
        return "ReportConfig{" +
                "width= " + width +
                ", height= " + height +
                ", number of columns= " + columnConfigs.size() +
                '}';
    }
}
