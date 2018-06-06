package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.Recommender;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecommenderMapper {

    @Insert("INSERT INTO recommenders(recommender_id,act_id) VALUES(#{recommender_id},#{act_id})")
    public void addRecommender(Recommender recommender);

    @Select("SELECT id,recommender_id FROM recommenders WHERE act_id = #{act_id}")
    public List<Recommender> findRecommenderByActid(Integer act_id);

    @Select("SELECT * FROM recommenders WHERE id = #{id}")
    public Recommender findById(Integer id);

    @Delete("DELETE FROM recommenders WHERE id = #{id}")
    public void delRecommender(Integer id);
}
