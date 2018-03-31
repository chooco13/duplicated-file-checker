package vo;

import lombok.Data;

@Data
public class FileInfo {
    private String path;
    private String name;
    private String type;
    private long size;

    public FileInfo(String path, String name, long size) {
        this.path = path;
        this.name = name;
        this.type = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInfo fileInfo = (FileInfo) o;
        if (size != fileInfo.size) return false;
        return type != null ? type.equals(fileInfo.type) : fileInfo.type == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }
}
