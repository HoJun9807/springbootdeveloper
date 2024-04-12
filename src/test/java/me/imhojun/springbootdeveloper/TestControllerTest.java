package me.imhojun.springbootdeveloper;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc를 사용하기 위한 어노테이션
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void deleteAll() {
        memberRepository.deleteAll();
    }

    @DisplayName("모든 멤버 조회")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "test"));

        // when
        final ResultActions result = mockMvc.perform(get(url) // 요청
            .accept(MediaType.APPLICATION_JSON)); // 응답 받을 타입

        // then
        result.andExpect(status().isOk()) // 상태값이 200인지 확인
            // jsonPath로 응답값 같은지 확인
            .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
            .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}