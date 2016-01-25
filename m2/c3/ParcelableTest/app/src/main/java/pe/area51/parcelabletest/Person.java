package pe.area51.parcelabletest;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String documentNumber;
    private final String gender;
    private final Location location;

    public Person(String firstName, String lastName, int age, String documentNumber, String gender, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.documentNumber = documentNumber;
        this.gender = gender;
        this.location = location;
    }

    public Person(Parcel source) {
        firstName = source.readString();
        lastName = source.readString();
        age = source.readInt();
        documentNumber = source.readString();
        gender = source.readString();
        location = source.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(age);
        dest.writeString(documentNumber);
        dest.writeString(gender);
        dest.writeParcelable(location, flags);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", documentNumber='" + documentNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", location=" + location +
                '}';
    }
}
