import {container} from "tsyringe";

export default function Inject(target : any, propertyKey : string | symbol) {
  let propertyType = Reflect.getMetadata("design:type", target, propertyKey);
  let propertyValue = container.resolve(propertyType);
  Object.defineProperty(target, propertyKey, {
    value    : propertyValue,
    writable : true
  });
}
