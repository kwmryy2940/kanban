package co.jp.kwmr.kanban.service;

import java.util.List;

import co.jp.kwmr.kanban.entity.TdTicketEntity;

public interface TdTicketService {
  public List<TdTicketEntity> getTickets(String sortBy, String order);

  // public List<TdTicketEntity> getJoinTickets();
  public void addTicketData(TdTicketEntity ticket);

  public void updateTicketById(TdTicketEntity ticket, Integer ticketId);

  public void deleteTicketById(Integer ticketId);
}
