package com.everisbootcamp.accountretire.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "retire")
public class Retire {

    @Id
    private String idretire;

    private String numberaccount;
    private Double amount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime datecreated = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Retire(String numberaccount, Double amount) {
        this.numberaccount = numberaccount;
        this.amount = amount;
    }
}
