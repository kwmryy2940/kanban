package co.jp.kwmr.kanban.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.jp.kwmr.kanban.entity.TdTicketEntity;
import co.jp.kwmr.kanban.service.TdTicketServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/v1")
@RestController
public class TdTicketController {

  private final TdTicketServiceImpl service;

  private TdTicketController(TdTicketServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/td_ticket")
  public List<TdTicketEntity> getTickets(@RequestParam(required = false, defaultValue = "id") String sortBy,
      @RequestParam(required = false, defaultValue = "ASC") String order) {
    List<TdTicketEntity> tdTicketList = service.getTickets(sortBy, order);
    return tdTicketList;
  }

  @PostMapping("/td_ticket")
  public void addTicketData(@RequestBody TdTicketEntity ticket) {
      service.addTicketData(ticket);
  }
}