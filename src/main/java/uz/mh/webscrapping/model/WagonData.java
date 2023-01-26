package uz.mh.webscrapping.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WagonData {
    private String wagonNumber;
    private String containerNumber;
    private String registerDate;
    private String registerPost;
    private String KKDG;
    private String dateOfRemoval;
    private String deRegistrationPost;
    private String sender;
    private String receiver;
}
