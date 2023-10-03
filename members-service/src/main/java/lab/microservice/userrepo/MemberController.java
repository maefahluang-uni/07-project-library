package lab.microservice.userrepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    // @Autowired
    // BookRepository bookRepository;

    // select all Member
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMember() {
        List<Member> concerts = memberRepository.findByOrderByFnameDesc();
        return ResponseEntity.ok(concerts);
    }

    // select Member by id
    @GetMapping("/members/{id}")
    public ResponseEntity getMemberById(@PathVariable long id) {
        Optional<Member> optMember = memberRepository.findById(id);

        // check if id is exists
        if (!optMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }

        // show Member by id
        Member member = optMember.get();

        // return success status
        return ResponseEntity.ok(member);
    }

    // select Member by name
    @GetMapping("/members/name/{name}")
    public ResponseEntity getMemberByName(@PathVariable String name) {
        // get Member
        List<Member> Members = memberRepository.findByFnameStartingWith(name);

        // chevk if Member is empty
        if (Members.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }

        // return success status
        return ResponseEntity.ok(Members);
    }

    // create Member
    @PostMapping("/members")
    public ResponseEntity createMember(@RequestBody Member member) {
        // add Member
        memberRepository.save(member);

        // return success message
        return ResponseEntity.ok("Member is created");
    }

    // update Member
    @PutMapping("/members")
    public ResponseEntity<String> updateMember(@RequestBody Member member) {
        // check if id not exists
        if (!memberRepository.existsById(member.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
        // update Member
        memberRepository.save(member);

        // return success message
        return ResponseEntity.ok("Member is updated");
    }

    // delete member by id
    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMemberById(@PathVariable long id) {
        // check if id exists in db
        if (!memberRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
        // delete Member by id
        memberRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Member is deleted");
    }

    // delete all Member
    @DeleteMapping("/members")
    public ResponseEntity deleteAllMember() {
        // delete all Member
        memberRepository.deleteAll();

        // return success message
        return ResponseEntity.ok("All of member are deleted");
    }

    // set when member borrow a book
    // @PutMapping("/{memberId}/borrow/{bookId}")
    // public ResponseEntity<String> borrowBook(@PathVariable Long memberId,
    // @PathVariable Long bookId) {
    // Optional<Book> optBook = bookRepository.findById(bookId);

    // if (!optBook.isPresent()) {
    // return ResponseEntity.notFound().build();
    // }

    // Book book = optBook.get();

    // Optional<Member> optMember = memberRepository.findById(memberId);

    // if (!optMember.isPresent()) {
    // return ResponseEntity.notFound().build();
    // }

    // Member member = optMember.get();

    // member.borrowedBooks(book);
    // bookRepository.save(book);
    // return ResponseEntity.ok("Book borrowed successfully.");
    // }
}
