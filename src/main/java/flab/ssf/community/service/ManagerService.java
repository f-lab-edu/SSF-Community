package flab.ssf.community.service;

import flab.ssf.community.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final MemberMapper memberMapper;

    public ManagerService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }




}
