import {ContainerConstructorFactory} from "./factories/container-constructor";
import {DefaultConstructorFactory}   from "./factories/default-constructor";
import {ComplexConstructorFactory}   from "./factories/complex-constructor";

// let gyreRegistry : LoaderRegistry;
//
// export function createGyreLoaderRegistry(
// ) : LoaderRegistry {
//   if (gyreRegistry) {
//     return gyreRegistry;
//   }
//   gyreRegistry = new LoaderRegistry();
//   gyreRegistry.register("overlay::position", new ContainerConstructorFactory());
//   gyreRegistry.register("default", new DefaultConstructorFactory());
//   gyreRegistry.register("complex-container", new ComplexConstructorFactory());
//   gyreRegistry.initialize();
//   return gyreRegistry;
// }
