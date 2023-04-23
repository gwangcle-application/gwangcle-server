package com.gwangcle.study.acceptance;

import com.gwangcle.study.acceptance.fixtures.MemberFixtures;
import com.gwangcle.study.acceptance.steps.MemberSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("멤버 관리 테스트")
public class MemberAcceptanceTest extends AcceptanceSetUp {

    /**
     * when: 멤버 한 명을 생성하면
     * then: http 201 상태 코드와 멤버 정보를 조회할 수 있다
     */
    @Test
    public void save() {
        //when
        ExtractableResponse<Response> response = MemberSteps.회원_생성_요청(MemberFixtures.NAME_ONE, MemberFixtures.EMPLOYMENT_STATUS_ONE, MemberFixtures.TASK_ONE);

        //then
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(MemberFixtures.NAME_ONE);
        Assertions.assertThat(response.jsonPath().getString("employmentStatus")).isEqualTo(MemberFixtures.EMPLOYMENT_STATUS_ONE);
        Assertions.assertThat(response.jsonPath().getString("task")).isEqualTo(MemberFixtures.TASK_ONE);
    }

    /**
     * given: 멤버 한 명을 생성하고
     * when: 생성된 멤버를 조회하면
     * then: http 200 상태 코드와 생성된 멤버 정보를 조회할 수 있다
     */
    @Test
    public void findById() {
        //given
        ExtractableResponse<Response> createResponse = MemberSteps.회원_생성_요청(MemberFixtures.NAME_ONE, MemberFixtures.EMPLOYMENT_STATUS_ONE, MemberFixtures.TASK_ONE);

        //when
        ExtractableResponse<Response> readResponse = MemberSteps.PK_값으로_회원_정보_요청(createResponse);

        //then
        Assertions.assertThat(readResponse.jsonPath().getString("name")).isEqualTo(MemberFixtures.NAME_ONE);
        Assertions.assertThat(readResponse.jsonPath().getString("employmentStatus")).isEqualTo(MemberFixtures.EMPLOYMENT_STATUS_ONE);
        Assertions.assertThat(readResponse.jsonPath().getString("task")).isEqualTo(MemberFixtures.TASK_ONE);
    }

    /**
     * given: 여러 명의 멤버를 생성하고
     * when: 멤버 전체를 조회하면
     * then: http 200 상태 코드와 멤버들의 정보를 조회할 수 있다
     */
    @Test
    public void findAll() {
        //given
        MemberSteps.회원_생성_요청(MemberFixtures.NAME_ONE, MemberFixtures.EMPLOYMENT_STATUS_ONE, MemberFixtures.TASK_ONE);
        MemberSteps.회원_생성_요청(MemberFixtures.NAME_TWO, MemberFixtures.EMPLOYMENT_STATUS_TWO, MemberFixtures.TASK_TWO);

        //when
        ExtractableResponse<Response> response = MemberSteps.회원_정보_전체_요청();

        //then
        Assertions.assertThat(response.jsonPath().getList("id").size()).isEqualTo(2);
        Assertions.assertThat(response.jsonPath().getList("name")).contains(MemberFixtures.NAME_ONE, MemberFixtures.NAME_TWO);
    }

    /**
     * given: 멤버 한 명을 생성하고
     * when+then: 생성된 멤버 삭제하면 http 204 상태 코드을 얻을 수 있다
     */
    @Test
    public void deleteById() {
        //given
        ExtractableResponse<Response> createResponse = MemberSteps.회원_생성_요청(MemberFixtures.NAME_ONE, MemberFixtures.EMPLOYMENT_STATUS_ONE, MemberFixtures.TASK_ONE);

        //when+then
        MemberSteps.PK_값으로_회원_삭제_요청(createResponse);
    }
}
