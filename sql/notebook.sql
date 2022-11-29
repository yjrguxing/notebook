DROP TABLE IF EXISTS menu_categroy;
CREATE TABLE menu_categroy(
                              id INT NOT NULL AUTO_INCREMENT  COMMENT '主键;主键 分类id' ,
                              name VARCHAR(32) NOT NULL   COMMENT '分类名称' ,
                              type INT NOT NULL   COMMENT '分类层级' ,
                              father_id INT NOT NULL   COMMENT '父分类ID' ,
                              logo VARCHAR(64)    COMMENT 'logo图片链接;图标链接' ,
                              CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
                              CREATED_TIME DATETIME    COMMENT '创建时间' ,
                              UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
                              UPDATED_TIME DATETIME    COMMENT '更新时间' ,
                              DELETE_BY VARCHAR(32)    COMMENT '删除人' ,
                              DELETE_TIME DATETIME    COMMENT '删除时间' ,
                              DELETE_FLAG TINYINT NOT NULL   COMMENT '是否已删除;0 未删除 1已删除' ,
                              PRIMARY KEY (id)
)  COMMENT = '菜单多级分类';
