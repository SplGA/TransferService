package memoryImpl;

import entity.Transfer;
import entity.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Splinner on 06.11.2016.
 */
public class Storage
{

    //<editor-fold desc="Storage_params">
    static final ConcurrentHashMap<Long, Transfer> transferMap = new ConcurrentHashMap<>();
    static final AtomicLong currentTransferId = new AtomicLong(0);
    static final ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();
    static final AtomicLong currentUserId = new AtomicLong(0);
    //</editor-fold>
}
