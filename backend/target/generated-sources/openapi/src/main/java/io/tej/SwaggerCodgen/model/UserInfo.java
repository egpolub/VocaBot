package io.tej.SwaggerCodgen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about User
 */
@ApiModel(description = "Information about User")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-03-22T00:29:22.929607700+03:00[Europe/Moscow]")
public class UserInfo   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("firstname")
  private String firstname;

  @JsonProperty("username")
  private String username;

  @JsonProperty("email")
  private String email;

  @JsonProperty("created")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime created;

  @JsonProperty("updated")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updated;

  public UserInfo id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * The user ID.
   * @return id
  */
  @ApiModelProperty(required = true, value = "The user ID.")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserInfo firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * The user firstname.
   * @return firstname
  */
  @ApiModelProperty(value = "The user firstname.")


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public UserInfo username(String username) {
    this.username = username;
    return this;
  }

  /**
   * The user name.
   * @return username
  */
  @ApiModelProperty(value = "The user name.")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserInfo email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The user email.
   * @return email
  */
  @ApiModelProperty(value = "The user email.")

@javax.validation.constraints.Email
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserInfo created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * The user created.
   * @return created
  */
  @ApiModelProperty(value = "The user created.")

  @Valid

  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public UserInfo updated(OffsetDateTime updated) {
    this.updated = updated;
    return this;
  }

  /**
   * The user last updated.
   * @return updated
  */
  @ApiModelProperty(value = "The user last updated.")

  @Valid

  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfo userInfo = (UserInfo) o;
    return Objects.equals(this.id, userInfo.id) &&
        Objects.equals(this.firstname, userInfo.firstname) &&
        Objects.equals(this.username, userInfo.username) &&
        Objects.equals(this.email, userInfo.email) &&
        Objects.equals(this.created, userInfo.created) &&
        Objects.equals(this.updated, userInfo.updated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, username, email, created, updated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

