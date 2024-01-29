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
    City: string;
    Country:string;
  };

  type Credentials = {
    email: string,
    password: string
  }

  export {User,Employee,Credentials}
