import { LoaderRegistry } from 'lib/designer/base/loader-registry';
import { ContainerConstructorFactory } from './factories/container-constructor';
import { DesignerManager } from 'lib/designer/core/designer-manager';
import { DefaultConstructorFactory } from './factories/default-constructor';
import { ComplexConstructorFactory } from './factories/complex-constructor';

let gyreRegistry: LoaderRegistry;

export function createGyreLoaderRegistry(
  manager: DesignerManager
): LoaderRegistry {
  if (gyreRegistry) {
    return gyreRegistry;
  }
  gyreRegistry = new LoaderRegistry(manager);
  gyreRegistry.register('overlay::position', new ContainerConstructorFactory());
  gyreRegistry.register('default', new DefaultConstructorFactory());
  gyreRegistry.register('complex-container', new ComplexConstructorFactory());
  gyreRegistry.initialize();
  return gyreRegistry;
}
