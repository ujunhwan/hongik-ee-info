package info.hongik.ee.domain.base;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
}
