package org.example.Model;

public class GroupTypeKey {
    public final String group;
    public final String type;

    public GroupTypeKey(String group, String type) {
        this.group = group;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupTypeKey that = (GroupTypeKey) o;

        if (!group.equals(that.group)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = group.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}