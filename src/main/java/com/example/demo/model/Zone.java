@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 THIS MUST MATCH DB COLUMN
    @Column(name = "zone_name", nullable = false)
    private String zoneName;

    @Column(nullable = false)
    private String location = "UNKNOWN";

    @Column(nullable = false)
    private Boolean active = true;

    public Zone() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
