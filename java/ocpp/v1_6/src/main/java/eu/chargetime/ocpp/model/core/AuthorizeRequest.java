package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.ModelUtil;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizeRequest implements Request {

  private static final int IDTAG_MAX_LENGTH = 20;
  private static final String ERROR_MESSAGE = "Exceeded limit of " + IDTAG_MAX_LENGTH + " chars";

  private String idTag;

  /** @deprecated use {@link #AuthorizeRequest(String)} to be sure to set required fields */
  @Deprecated
  public AuthorizeRequest() {}

  /**
   * Handle required fields.
   *
   * @param idTag authorize id, see {@link #setIdTag(String)}
   */
  public AuthorizeRequest(String idTag) {
    setIdTag(idTag);
  }

  /**
   * This contains the identifier that needs to be authorized.
   *
   * @return String, max 20 characters. Case insensitive.
   */
  public String getIdTag() {
    return idTag;
  }

  /**
   * Required. This contains the identifier that needs to be authorized.
   *
   * @param idTag String, max 20 characters. Case insensitive.
   */
  @XmlElement
  public void setIdTag(String idTag) {
    if (!ModelUtil.validate(idTag, IDTAG_MAX_LENGTH)) {
      throw new PropertyConstraintException(idTag.length(), ERROR_MESSAGE);
    }

    this.idTag = idTag;
  }

  @Override
  public boolean validate() {
    return ModelUtil.validate(idTag, IDTAG_MAX_LENGTH);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthorizeRequest request = (AuthorizeRequest) o;
    return Objects.equals(idTag, request.idTag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTag);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("idTag", idTag)
        .add("isValid", validate())
        .toString();
  }
}
