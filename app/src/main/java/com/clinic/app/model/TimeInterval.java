package com.clinic.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TimeInterval {
    @Column
    @Temporal(TemporalType.TIME)
    private Date intervalStart;
    @Column
    @Temporal(TemporalType.TIME)
    private Date intervalEnd;
}
