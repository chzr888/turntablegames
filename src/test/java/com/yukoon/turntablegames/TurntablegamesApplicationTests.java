package com.yukoon.turntablegames;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.UsersMapper;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.DrawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurntablegamesApplicationTests {
	@Test
	public void contextLoads() throws JsonProcessingException {
	}

}
