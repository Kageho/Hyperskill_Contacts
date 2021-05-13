package contacts.contactTypes.organization;

import contacts.contactTypes.Contact;

public class Organization extends Contact {
    private String address;

    private Organization(String name, String address, String phone) {
        super(name, phone, false);
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + (address == null ? 113 : address.hashCode());
        result = 31 * result + (phone == null ? 13 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (!(obj instanceof Organization)) {
            return false;
        } else {
            Organization org = (Organization) obj;
            return org.hashCode() == hashCode();
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Organization name: ").append(name).append("\n");
        info.append("Address: ").append(!address.isBlank() ? address : "[no data]").append("\n");
        info.append("Number: ").append(hasNumber ? phone : "[no data]").append("\n");
        info.append("Time created: ").append(timeCreated).append("\n");
        info.append("Time last edit: ").append(timeUpdated);
        return info.toString();
    }

    public static class OrganizationBuilder {
        private String address;
        private String name;
        private String phone;

        public OrganizationBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public OrganizationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public OrganizationBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Organization build() {
            return new Organization(name, address, phone);
        }
    }

}
