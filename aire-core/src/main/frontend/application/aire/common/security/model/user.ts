import {Token}    from "./token";
import {Property} from "../../../condensation/condensation";
import {Image}    from "../../../designer/core";

export class Principal {
  id : string;
  active : boolean;
  lastName : string;
  firstName : string;
  username : string;
  emailAddress : string;
  phoneNumber : string;
  roles : Role[];

  constructor(value? : any) {
    Object.assign(this, value);
    if (value) {
      this.emailAddress = value["email-address"];
      this.phoneNumber = value["phone-number"];
      this.firstName = value["first-name"];
      this.lastName = value["last-name"];
    }
  }
}

export class Authentication {
  token : Token;
  user : Principal;

  constructor(value? : any) {
    if (value) {
      this.token = new Token(value.token);
      this.user = new Principal(value.principal);
    }
  }
}

export class Role {
  constructor(public readonly authority? : string) {
  }
}

export interface ProtectedObject {
  rolesAllowed : Role[];
  rolesDenied : Role[];
}

export class RegistrationConfirmation {
  expires : string;

  @Property("requested-on")
  requestedOn : string;

  @Property("registration-id")
  registrationId : string;

  @Property({
    alias : "principal",
    type  : Principal
  })
  principal : Principal;
}

export class RegistrationRequest {
  type : string =
    "io.sunshower.sdk.v1.model.core.security.RegistrationRequestElement";

  @Property("registration-id")
  id : string;

  username : string;

  password : string;

  @Property("phone-number")
  phoneNumber : string;

  @Property("email-address")
  emailAddress : string;

  @Property("last-name")
  lastName : string;

  @Property("first-name")
  firstName : string;

  @Property({
    alias : "image",
    type  : Image
  })
  image : Image;

  constructor(value? : any) {
    if (value) {
      Object.assign(this, value);
    }

  }
}

export class AuthenticationRequest {
  public username : string;
  public password : string;

  constructor(value? : any) {
    Object.assign(this, value);
  }
}
