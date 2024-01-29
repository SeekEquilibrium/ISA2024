type User = {
  id: string;
  name: string;
  surname: string;
  gender: string;
  phoneNumber: string;
  email: string;
  isVerified: boolean;
  role: Role;
};

type Role = {
  id: string;
  authority: string;
  name: string;
};

type Employee = {
    name: string;
    surname: string;
    gender: string;
    phoneNumber: string;
    email: string;
    isVerified: boolean;
    occupation:string;
    companyInfo:string;
    street:string;
    city: string;
    country:string;
  };

  type EditEmployee = {
    name: string;
    surname: string;
    email: string;
    phoneNumber: string;
    isVerified: boolean;
    occupation:string;
    companyInfo:string;
    street:string;
    city: string;
    country:string;
  };

  type Credentials = {
    email: string,
    password: string
  }

  export {User,Employee,Credentials,EditEmployee}
