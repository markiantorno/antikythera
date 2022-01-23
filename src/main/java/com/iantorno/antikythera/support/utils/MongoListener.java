package com.iantorno.antikythera.support.utils;

import com.iantorno.antikythera.model.BaseNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MongoListener extends AbstractMongoEventListener<BaseNode> {

  Logger logger = LoggerFactory.getLogger(MongoListener.class);

  @Override
  public void onBeforeConvert(BeforeConvertEvent<BaseNode> event) {
    super.onBeforeConvert(event);
    BaseNode node = event.getSource();
    logger.info("Base node <" + node.getTitle() + "> received.");
    Date dateNow = new Date();
    if (node.getCreatedAt() == null) {
      node.setCreatedAt(dateNow);
      logger.info("Node <{}> has no created date, setting as {}.", node.title, dateNow);
    }
    node.setUpdatedAt(dateNow);
    logger.info("Node <{}> setting updated date to {}.", node.title, dateNow);
  }

}
