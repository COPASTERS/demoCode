package com.cy.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CHANGE_NET_MONTH")
@DynamicInsert
@DynamicUpdate
public class ChangeNetMonth implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String npcode;
    private String portoutnetid;
    private String portinnetid;
    private String homenetid;
    private String reqdatetime;

}
