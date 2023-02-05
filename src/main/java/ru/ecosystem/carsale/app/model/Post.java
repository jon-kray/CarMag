package ru.ecosystem.carsale.app.model;

import lombok.*;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
@Entity
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "post-entity-graph-info",
                attributeNodes = {
                        @NamedAttributeNode("description"),
                        @NamedAttributeNode("photo"),
                }
        ),
        @NamedEntityGraph(
                name = "post-entity-graph-event",
                attributeNodes = {
                        @NamedAttributeNode("photo"),
                        @NamedAttributeNode("user")
                }
        )
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CAR_ID")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "price")
    private String price;

    @Column(name = "DESCRIPTION")
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Column(name = "CREATED")
    private LocalDateTime created;

    @Column(name = "PHOTO")
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    @Column(name = "SELL_STATUS")
    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;




}
