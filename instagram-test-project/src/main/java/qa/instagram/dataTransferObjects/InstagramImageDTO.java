package qa.instagram.dataTransferObjects;

import java.util.Date;

public class InstagramImageDTO {
  final String id;
  final long timestamp;
  final byte[] image;

  private InstagramImageDTO(String id, byte[] content) {
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

  public static InstagramImageDTO create(String fileName, byte[] data) {
    return new InstagramImageDTO(fileName, data);
  }
}

