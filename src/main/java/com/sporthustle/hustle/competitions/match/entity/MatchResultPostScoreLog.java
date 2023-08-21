package com.sporthustle.hustle.competitions.match.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.user.entity.User;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "MatchResultPostScoreLog")
@Entity
public class MatchResultPostScoreLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(columnDefinition = "SMALLINT UNSIGNED default 0")
  private Long score;

  @Type(type = "json")
  @Column(nullable = false, columnDefinition = "json")
  private Map<String, Object> extra = new HashMap<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "match_result_post_id", nullable = false)
  private MatchResultPost matchResultPost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}
