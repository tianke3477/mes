package com.qcadoo.mes.view;

public interface ViewDefinitionState extends ContainerState {

    void performEvent(String path, String event, String... args);

    ComponentState getComponentByPath(String path);

    ComponentState getComponentByFunctionalPath(String path);

    void redirectTo(String redirectToUrl, boolean openInNewWindow);

    void registerComponent(String path, ComponentState state);

}
