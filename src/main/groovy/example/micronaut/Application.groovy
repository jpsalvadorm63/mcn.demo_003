package example.micronaut

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic
import io.micronaut.runtime.server.event.ServerStartupEvent

@CompileStatic
class Application implements ApplicationEventListener<ServerStartupEvent> {

    private final RegisterUseCase registerUseCase

    Application(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase
    }

    static void main(String[] args) {
        Micronaut.run(Application)
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        try {
            registerUseCase.register("harry@micronaut.example")
            Thread.sleep(20000)
            registerUseCase.register("ron@micronaut.example")
        } catch (InterruptedException e) {
            e.printStackTrace()
        }
    }
}
