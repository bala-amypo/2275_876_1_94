@Entity
@Table(name = "bin")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // 🔑 DEFAULT VALUE ADDED
    @Column(name = "capacity_liters", nullable = false)
    private Integer capacityLiters = 100;

    @Column(nullable = false)
    private Boolean active = true;

    public Bin() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Integer capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
