package leveloper.jpabook.jpashop.controller.dto;

import leveloper.jpabook.jpashop.domain.Address;
import leveloper.jpabook.jpashop.domain.Member;
import lombok.Getter;

@Getter
public class MembersResponseDto {
    private Long id;
    private String name;
    private Address address;

    public MembersResponseDto(Member entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
    }
}
