
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.PlayerService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestPlayerController {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PlayerService playerService;

    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testEntry() throws Exception {
        mvc.perform(post("/entry").param("name", "plName")).andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andReturn().getResponse();
    }

    @Test
    public void testGameTable() throws Exception {
       int id = playerService.addPlayerAndGetId("Dario");
        mvc.perform(get("/player/{id}", id)).andExpect(status().isOk())
                .andExpect(view().name("table"));
    }

 /*   @Test
    @Ignore
    public void testGetPlayer() throws Exception {
        mvc.perform(get("/player/{id}/getPlayer", 1))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Mario"));
    }*/

    /*@Test
    public void testUpdateCash() throws Exception {
        mvc.perform(post("/player/{id}/updateCash.json", 1).accept(MediaType.APPLICATION_JSON).param("cash", "15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andReturn().getResponse();
    }*/


}
