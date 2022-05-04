package com.trikorasolutions.keycloak.client;

import com.trikorasolutions.keycloak.client.bl.KeycloakClientLogic;
import com.trikorasolutions.keycloak.client.dto.KeycloakUserRepresentation;
import com.trikorasolutions.keycloak.client.dto.RoleRepresentation;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.List;

import static com.trikorasolutions.keycloak.client.TrikoraKeycloakClientInfo.ADM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class LogicRoleTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogicRoleTest.class);

  @Inject
  KeycloakClientLogic keycloakClientLogic;

  @Inject
  TrikoraKeycloakClientInfo tkrKcCli;

  @Test
  public void testGetRoleUsers() {
    String accessToken = tkrKcCli.getAccessToken(ADM);

    List<KeycloakUserRepresentation> logicResponse = keycloakClientLogic.getAllUsersInAssignedRole(
        tkrKcCli.getRealmName(), accessToken, tkrKcCli.getClientId(), "hr").await().indefinitely();
    assertThat(logicResponse.size(), is(greaterThanOrEqualTo(1)));
  }

  @Test
  public void testGetUserRoles() {
    String accessToken = tkrKcCli.getAccessToken(ADM);
    List<RoleRepresentation> logicResponse = keycloakClientLogic.getUserRoles(
        tkrKcCli.getRealmName(), accessToken, tkrKcCli.getClientId(), ADM).await().indefinitely();
    assertThat(logicResponse.size(), is(greaterThanOrEqualTo(1)));
  }

  @Test
  public void testGetRoleInfo() {
    String accessToken = tkrKcCli.getAccessToken(ADM);

    List<RoleRepresentation> logicResponse = keycloakClientLogic.getUserRoles(
        tkrKcCli.getRealmName(), accessToken, tkrKcCli.getClientId(), ADM).await().indefinitely();
    LOGGER.warn("RESPONSE {}", logicResponse);
    assertThat(logicResponse.size(), is(greaterThanOrEqualTo(1)));
  }


}