package co.jp.kwmr.kanban.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import co.jp.kwmr.kanban.entity.TdTicketEntity;
import co.jp.kwmr.kanban.repository.TdTicketRepository;

@Service
public class TdTicketServiceImpl implements TdTicketService {

  @Autowired
  TdTicketRepository tdTicketRepository;

  @Override
  public List<TdTicketEntity> getTickets(String sortBy, String order) {
    Sort.Direction direction = order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    Sort sort = Sort.by(direction, sortBy);

    Iterable<TdTicketEntity> tdTicketIterable = tdTicketRepository.findAll(sort);
    List<TdTicketEntity> tdTicketList = new ArrayList<>();
    tdTicketIterable.forEach(tdTicketList::add);

    return tdTicketList;
  }

  // @Override
  // public List<TdTicketEntity> getJoinTickets() {
  //   List<TdTicketEntity> tdTicketList = tdTicketRepository.findJoinAll();
  //   return tdTicketList;
  // }

  @Override
  public void addTicketData(TdTicketEntity ticket) {

    // statusはデフォルトで0をセット
    ticket.setStatus(0);

    // システムから現在時刻を取得
    LocalDateTime now = LocalDateTime.now();

    // 登録日時、更新日時に現在時刻をセット
    ticket.setCreatedAt(now);
    ticket.setUpdatedAt(now);

    // DBに登録
    tdTicketRepository.save(ticket);
  }

}
