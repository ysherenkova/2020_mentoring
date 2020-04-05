package qa.instagram.dataTransferObjects;

import java.util.Date;

public class InstagramImageDTO {
  final String id;
  final long timestamp;
  final byte[] image;

  public InstagramImageDTO(String id, byte[] content) {
    this.id = id;
    this.timestamp = new Date().getTime();
    image = content;
  }

  public String getId() {
    return id;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public byte[] getImage() {
    return image;
  }
}

